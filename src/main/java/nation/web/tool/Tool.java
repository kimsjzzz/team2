// verison 0.8
package nation.web.tool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

public class Tool {
  /**
   * HTML 특수 문자의 변경
   * @param str
   * @return
   */
  public static synchronized String convertChar(String str) {
    str = str.replaceAll("<", "&lt;");
    str = str.replaceAll(">", "&gt;");
    str = str.replaceAll("'", "&apos;"); // &#39;
    str = str.replaceAll("\"", "&quot;");
    str = str.replaceAll("\r\n", "<BR>");
    
    return str;
  }
  
  /**
   * 문자열의 길이가 length 보다 크면 "..."을 표시하는 메소드
   * @param str 원본 문자열
   * @param length 선별할 문자열 길이
   * @return 특정 길이의 문자열
   */
  public static synchronized String textLength(String str, int length) {
    if (str != null) {
      if (str.length() > length) {
        str = str.substring(0,  length) + "..."; // 범위: 0 ~ length - 1
      }
    } else {
      str = "";
    }
    
    return str;
  }
  
  /**
   * 수를 전달받아 자료의 단위를 적용합니다.
   * @param size
   * @return 1000 > 1000 Byte
   */
  public static synchronized String unit(long size){
    String str = "";
    
    if (size < 1024){ // 1 KB 이하
      str = size + " Byte";
    }else if (size < 1024 * 1024){ // 1 MB 이하
      str = (int)(Math.ceil(size/1024.0)) + " KB"; // 1048576 보다 작으면 KB
    }else if (size < 1024 * 1024 * 1024){ // 1 GB 이하
      str = (int)(Math.ceil(size/1024.0/1024.0)) + " MB";
    }else if (size < 1024L * 1024 * 1024 * 1024){ // 1 TB 이하
      str = (int)(Math.ceil(size/1024.0/1024.0/1024.0)) + " GB";
    }else if (size < 1024L * 1024 * 1024 * 1024 * 1024){ // 1 PT 이하
      str = (int)(Math.ceil(size/1024.0/1024.0/1024.0/1024.0)) + " TB";
    }else if (size < 1024L * 1024 * 1024 * 1024 * 1024 * 1024){ // 1 EX 이하
      str = (int)(Math.ceil(size/1024.0/1024.0/1024.0/1024.0/1024.0)) + " PT";
    }
    
    return str;
  }
   
  /**
   * 이미지 사이즈를 변경하여 새로운 Preview 이미지를 생성합니다.
   <pre>
   사용예): Too.preview(folder 명, 원본 파일명, 200, 150)
   </pre>
   * @param upDir 원본 이미지 폴더
   * @param _src 원본 파일명
   * @param width 생성될 이미지 너비
   * @param height  생성될 이미지 높이, ImageUtil.RATIO는 자동 비례 비율
   * @return src.jpg 파일을 이용하여 src_t.jpg 파일을 생성하여 파일명 리턴
   */
  public static synchronized String preview(String upDir, String _src, int width,
      int height) {
    int RATIO = 0;  // 비율 변경 없음
    int SAME = -1;  // 크기 변경 없음.

    File src = new File(upDir + "/" + _src); // 원본 파일 객체 생성
    String srcname = src.getName(); // 원본 파일명 추출

    // 순수 파일명 추출, mt.jpg -> mt 만 추출
    String _dest = srcname.substring(0, srcname.indexOf("."));

    // 축소 이미지 조합 /upDir/mt_t.jpg
    File dest = new File(upDir + "/" + _dest + "_t.jpg");

    Image srcImg = null;

    String name = src.getName().toLowerCase(); // 파일명을 추출하여 소문자로 변경
    
    // 이미지 파일인지 검사
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // 메모리에 원본 이미지 생성, Call By Reference
        int srcWidth = srcImg.getWidth(null); // 원본 이미지 너비 추출
        int srcHeight = srcImg.getHeight(null); // 원본 이미지 높이 추출
        int destWidth = -1, destHeight = -1; // 대상 이미지 크기 초기화

        if (width == SAME) {     // width가 -1인 경우
          destWidth = srcWidth; // 원본 크기 사용
        } else if (width > 0) {
          destWidth = width;     // 새로운 width를 할당
        }

        if (height == SAME) {       // height가 -1인 경우
          destHeight = srcHeight;  // 원본 크기 사용
        } else if (height > 0) {
          destHeight = height;      // 새로운 높이로 할당
        }

        // 비율에 따른 크기 계산
        if (width == RATIO && height == RATIO) {
          destWidth = srcWidth;
          destHeight = srcHeight;
        } else if (width == RATIO) {
          double ratio = ((double) destHeight) / ((double) srcHeight);
          destWidth = (int) ((double) srcWidth * ratio);
        } else if (height == RATIO) {
          double ratio = ((double) destWidth) / ((double) srcWidth);
          destHeight = (int) ((double) srcHeight * ratio);
        }

        // 메모리에 대상 이미지 생성
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);

        pg.grabPixels();

        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);

        // 파일에 기록
        ImageIO.write(destImg, "jpg", dest);

        // System.out.println(dest.getName() + " 이미지를 생성했습니다.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return dest.getName();
  }
  
  /**
   * 이미지인지 검사
   * @param file
   * @return
   */
  public static synchronized boolean isImage(String file) {
    boolean sw = false;
 
    if (file != null) { // 문자열이 있다면
      file = file.toLowerCase(); // 소문자로 변환
      if (file.endsWith(".jpg") || file.endsWith(".jpeg") || file.endsWith(".png") || file.endsWith(".gif")) {
        sw = true;
      }
    }
    return sw;
  }

  /**
   * 문자열이 null이면 ""으로 변경
   * @param str
   * @return
   */
  public static synchronized String checkNull(String str) {
    if (str == null) {
      str = "";
    }
    
    return str;
  }

  /**
   * 파일을 삭제합니다.
   */
  public static synchronized boolean deleteFile(String folder, String fileName) {
    boolean ret = false;

    try {
      if (fileName != null) { // 기존에 파일이 존재하는 경우 삭제
        File file = new File(folder + "/" + fileName);
        if (file.exists() && file.isFile()) { // 존재하는 파일인지 검사
          ret = file.delete();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return ret;
  }

  /**
   * 천단위 구분 기호 추가
   * @param price
   * @return
   */
  public static synchronized String comma(long price) {
    DecimalFormat df = new DecimalFormat("#,###,##0");
    String cs = df.format(price);
    
    return cs;
  }
  
  /**
   * 키를 조합합니다. 예) ABC + 현재시간: ABC1234567890123
   * @return
   */
  public static String key(){
    String code = "";
    
    //  ASCII: 65 ~ 90, (A ~ Z: 26자)
    Random rnd = new Random();
    int su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su; // 문자로 변환
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;

    code = code + new Date().getTime(); // 1970년 1월 1일 이후 1000을 1초로 표현
    // System.out.println("CODE: " + code); // CODE: XGL1449819012230 
    
    return code;
  }
  
  /**
   * 폴더를 입력받아 절대 경로를 산출합니다. 
   * 예) getRealPath(request, "/media/storage")
   * 
   * @param request
   * @param dir 절대 경로를 구할 폴더명
   * @return 절대 경로 리턴
   * @throws IOException
   */
  public static synchronized String getRealPath(HttpServletRequest request, String dir) {
    String path = "";
    
    try{
      path = request.getRealPath(dir) + "/";  
      // System.out.println("Upload path: " + path);
    }catch(Exception e){
      System.out.println(e.toString());
    }

    return path;
  }

  /**
   * 파일 삭제
   * @param fname
   * @return
   */
  public static synchronized boolean deleteFile(String fname) {
    File file = new File(fname);
    boolean ret = false;
    
      if (file.exists()){
        ret = file.delete();
      }
    
    return ret;
  }
  
  /**
   * 주어진 날짜와 기간을 계산하여 새글 여부 판단
   * 현재 날짜가 2013-02-04, 글을 등록날짜 2013-02-02
   * isNew("2013-02-04", 2) : 글 작성 후 2틀 전까지 새글 처리 
   * 
   * @param date 문자열로 된 날짜
   * @param period 새글로 지정할 기간
   * @return
   */
  public static synchronized boolean isNew(String date, int period) {
    boolean sw = false;
 
    Date _date = new Date();
    // 숫자 형태인 날짜를 '년월일 시분초'의 형태로 변환
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    try {
      _date = sd.parse(date);
    } catch (Exception e) {
    }
    // System.out.println(date);
    // 1970년1월1일부터 시간을 1000을 1초로하여 계산하여 리턴
    long time = _date.getTime(); // 글을 작성한 시간
 
    // 현재 시간을 1970년 1월 1일부터 수치형식으로 리턴
    long currentTime = System.currentTimeMillis();
 
    // 현재 시간과 글 등록시간의 차이를 계산
    long diff = currentTime - time;
 
    // 1일 86,400,000: 1초를 1000으로 하루를 계산
 
    // ceil: 아주 작은 소수점이 있어도 정수로 반올림 처리됨.
    // 0.0001 --> 1: 오늘 날짜
    // 1.00002 --> 2: 어제 날짜
    int day = (int) Math.ceil(((double) diff / 86400000)); // 지난 날짜 계산
 
    if (day <= period) {
      sw = true; // 최신글 처리
    }
    return sw;
  }
  

  /**
   * 2010-12-14 형식의 날짜를 리턴합니다.
   * @return 2008-01-30 형식의 문자열 리턴
   */
  public static String getDate(){
      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
      
      String date = sd.format(new Date());
 
      // System.out.println(date);        
      return date;
  }
 
  /**
   * 20101214 형식의 날짜를 리턴합니다.
   * @return 20101214 형식의 문자열 리턴
   */
  public static String getDate2(){
      SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
      
      String date = sd.format(new Date());
 
      // System.out.println(date);        
      return date;
  }
  
  /**
   * 20101214 형식의 날짜를 리턴합니다.
   * @param _date
   * @return
   */
  public static String getDate3(Date _date){
      SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
      String date = sd.format(_date);
 
      // System.out.println(date);        
      return date;
  }
  
  /**
   * 2010년 12월 14일 형식의 날짜를 리턴합니다.
   * @return
   */
  public static String getDate4(){
      SimpleDateFormat sd = new SimpleDateFormat("yyyy년 MM월 dd일");
      String date = sd.format(new Date());
 
      // System.out.println(date);        
      return date;
  }
 
  /**
   * 20101214일 형식의 날짜를 리턴합니다.
   * @param _date
   * @return
   */
  public static String getDate5(Date _date){
      SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
      String date = sd.format(_date);
 
      // System.out.println(date);        
      return date;
  }
 
  /**
   * 2017-06-20 12:30
   * @return
   */
  public static String getDate6(){
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    String date = sd.format(new Date());
 
    // System.out.println(date);        
    return date;
  }
  
  /**
   * 2017-06-20 12:30
   * @param hhmm 시분을 전달받아 설정
   * @return
   */
  public static String getDate7(String hhmm){
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd " + hhmm);
    String date = sd.format(new Date());
 
    // System.out.println(date);        
    return date;
  }
  
  /**
   * 2017-06-20 12:30
   * @param datestr
   * @return
   */
  public static Date getDate8(String datestr){
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    Date date = null;
    try {
      date = sd.parse(datestr);
    } catch (ParseException e) {
     
      e.printStackTrace();
    }
 
         
    return date;
  }
  
  public static void main(String[] args){
    Date date = getDate8("2019-01-01 12:00");
    System.out.println(date.toLocaleString());
    long start = date.getTime();
    
    date = getDate8("2019-01-01 06:00");
    System.out.println(date.toLocaleString());
    long end = date.getTime();
    
    if(end - start > 0) {
      System.out.println("날짜가 늦을 수 없습니다.");
    } else{
      System.out.println("등록 가능한 날짜입니다");
    }
  }
  
}







