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
   * HTML Ư�� ������ ����
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
   * ���ڿ��� ���̰� length ���� ũ�� "..."�� ǥ���ϴ� �޼ҵ�
   * @param str ���� ���ڿ�
   * @param length ������ ���ڿ� ����
   * @return Ư�� ������ ���ڿ�
   */
  public static synchronized String textLength(String str, int length) {
    if (str != null) {
      if (str.length() > length) {
        str = str.substring(0,  length) + "..."; // ����: 0 ~ length - 1
      }
    } else {
      str = "";
    }
    
    return str;
  }
  
  /**
   * ���� ���޹޾� �ڷ��� ������ �����մϴ�.
   * @param size
   * @return 1000 > 1000 Byte
   */
  public static synchronized String unit(long size){
    String str = "";
    
    if (size < 1024){ // 1 KB ����
      str = size + " Byte";
    }else if (size < 1024 * 1024){ // 1 MB ����
      str = (int)(Math.ceil(size/1024.0)) + " KB"; // 1048576 ���� ������ KB
    }else if (size < 1024 * 1024 * 1024){ // 1 GB ����
      str = (int)(Math.ceil(size/1024.0/1024.0)) + " MB";
    }else if (size < 1024L * 1024 * 1024 * 1024){ // 1 TB ����
      str = (int)(Math.ceil(size/1024.0/1024.0/1024.0)) + " GB";
    }else if (size < 1024L * 1024 * 1024 * 1024 * 1024){ // 1 PT ����
      str = (int)(Math.ceil(size/1024.0/1024.0/1024.0/1024.0)) + " TB";
    }else if (size < 1024L * 1024 * 1024 * 1024 * 1024 * 1024){ // 1 EX ����
      str = (int)(Math.ceil(size/1024.0/1024.0/1024.0/1024.0/1024.0)) + " PT";
    }
    
    return str;
  }
   
  /**
   * �̹��� ����� �����Ͽ� ���ο� Preview �̹����� �����մϴ�.
   <pre>
   ��뿹): Too.preview(folder ��, ���� ���ϸ�, 200, 150)
   </pre>
   * @param upDir ���� �̹��� ����
   * @param _src ���� ���ϸ�
   * @param width ������ �̹��� �ʺ�
   * @param height  ������ �̹��� ����, ImageUtil.RATIO�� �ڵ� ��� ����
   * @return src.jpg ������ �̿��Ͽ� src_t.jpg ������ �����Ͽ� ���ϸ� ����
   */
  public static synchronized String preview(String upDir, String _src, int width,
      int height) {
    int RATIO = 0;  // ���� ���� ����
    int SAME = -1;  // ũ�� ���� ����.

    File src = new File(upDir + "/" + _src); // ���� ���� ��ü ����
    String srcname = src.getName(); // ���� ���ϸ� ����

    // ���� ���ϸ� ����, mt.jpg -> mt �� ����
    String _dest = srcname.substring(0, srcname.indexOf("."));

    // ��� �̹��� ���� /upDir/mt_t.jpg
    File dest = new File(upDir + "/" + _dest + "_t.jpg");

    Image srcImg = null;

    String name = src.getName().toLowerCase(); // ���ϸ��� �����Ͽ� �ҹ��ڷ� ����
    
    // �̹��� �������� �˻�
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // �޸𸮿� ���� �̹��� ����, Call By Reference
        int srcWidth = srcImg.getWidth(null); // ���� �̹��� �ʺ� ����
        int srcHeight = srcImg.getHeight(null); // ���� �̹��� ���� ����
        int destWidth = -1, destHeight = -1; // ��� �̹��� ũ�� �ʱ�ȭ

        if (width == SAME) {     // width�� -1�� ���
          destWidth = srcWidth; // ���� ũ�� ���
        } else if (width > 0) {
          destWidth = width;     // ���ο� width�� �Ҵ�
        }

        if (height == SAME) {       // height�� -1�� ���
          destHeight = srcHeight;  // ���� ũ�� ���
        } else if (height > 0) {
          destHeight = height;      // ���ο� ���̷� �Ҵ�
        }

        // ������ ���� ũ�� ���
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

        // �޸𸮿� ��� �̹��� ����
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);

        pg.grabPixels();

        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);

        // ���Ͽ� ���
        ImageIO.write(destImg, "jpg", dest);

        // System.out.println(dest.getName() + " �̹����� �����߽��ϴ�.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return dest.getName();
  }
  
  /**
   * �̹������� �˻�
   * @param file
   * @return
   */
  public static synchronized boolean isImage(String file) {
    boolean sw = false;
 
    if (file != null) { // ���ڿ��� �ִٸ�
      file = file.toLowerCase(); // �ҹ��ڷ� ��ȯ
      if (file.endsWith(".jpg") || file.endsWith(".jpeg") || file.endsWith(".png") || file.endsWith(".gif")) {
        sw = true;
      }
    }
    return sw;
  }

  /**
   * ���ڿ��� null�̸� ""���� ����
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
   * ������ �����մϴ�.
   */
  public static synchronized boolean deleteFile(String folder, String fileName) {
    boolean ret = false;

    try {
      if (fileName != null) { // ������ ������ �����ϴ� ��� ����
        File file = new File(folder + "/" + fileName);
        if (file.exists() && file.isFile()) { // �����ϴ� �������� �˻�
          ret = file.delete();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return ret;
  }

  /**
   * õ���� ���� ��ȣ �߰�
   * @param price
   * @return
   */
  public static synchronized String comma(long price) {
    DecimalFormat df = new DecimalFormat("#,###,##0");
    String cs = df.format(price);
    
    return cs;
  }
  
  /**
   * Ű�� �����մϴ�. ��) ABC + ����ð�: ABC1234567890123
   * @return
   */
  public static String key(){
    String code = "";
    
    //  ASCII: 65 ~ 90, (A ~ Z: 26��)
    Random rnd = new Random();
    int su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su; // ���ڷ� ��ȯ
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;

    code = code + new Date().getTime(); // 1970�� 1�� 1�� ���� 1000�� 1�ʷ� ǥ��
    // System.out.println("CODE: " + code); // CODE: XGL1449819012230 
    
    return code;
  }
  
  /**
   * ������ �Է¹޾� ���� ��θ� �����մϴ�. 
   * ��) getRealPath(request, "/media/storage")
   * 
   * @param request
   * @param dir ���� ��θ� ���� ������
   * @return ���� ��� ����
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
   * ���� ����
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
   * �־��� ��¥�� �Ⱓ�� ����Ͽ� ���� ���� �Ǵ�
   * ���� ��¥�� 2013-02-04, ���� ��ϳ�¥ 2013-02-02
   * isNew("2013-02-04", 2) : �� �ۼ� �� 2Ʋ ������ ���� ó�� 
   * 
   * @param date ���ڿ��� �� ��¥
   * @param period ���۷� ������ �Ⱓ
   * @return
   */
  public static synchronized boolean isNew(String date, int period) {
    boolean sw = false;
 
    Date _date = new Date();
    // ���� ������ ��¥�� '����� �ú���'�� ���·� ��ȯ
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    try {
      _date = sd.parse(date);
    } catch (Exception e) {
    }
    // System.out.println(date);
    // 1970��1��1�Ϻ��� �ð��� 1000�� 1�ʷ��Ͽ� ����Ͽ� ����
    long time = _date.getTime(); // ���� �ۼ��� �ð�
 
    // ���� �ð��� 1970�� 1�� 1�Ϻ��� ��ġ�������� ����
    long currentTime = System.currentTimeMillis();
 
    // ���� �ð��� �� ��Ͻð��� ���̸� ���
    long diff = currentTime - time;
 
    // 1�� 86,400,000: 1�ʸ� 1000���� �Ϸ縦 ���
 
    // ceil: ���� ���� �Ҽ����� �־ ������ �ݿø� ó����.
    // 0.0001 --> 1: ���� ��¥
    // 1.00002 --> 2: ���� ��¥
    int day = (int) Math.ceil(((double) diff / 86400000)); // ���� ��¥ ���
 
    if (day <= period) {
      sw = true; // �ֽű� ó��
    }
    return sw;
  }
  

  /**
   * 2010-12-14 ������ ��¥�� �����մϴ�.
   * @return 2008-01-30 ������ ���ڿ� ����
   */
  public static String getDate(){
      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
      
      String date = sd.format(new Date());
 
      // System.out.println(date);        
      return date;
  }
 
  /**
   * 20101214 ������ ��¥�� �����մϴ�.
   * @return 20101214 ������ ���ڿ� ����
   */
  public static String getDate2(){
      SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
      
      String date = sd.format(new Date());
 
      // System.out.println(date);        
      return date;
  }
  
  /**
   * 20101214 ������ ��¥�� �����մϴ�.
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
   * 2010�� 12�� 14�� ������ ��¥�� �����մϴ�.
   * @return
   */
  public static String getDate4(){
      SimpleDateFormat sd = new SimpleDateFormat("yyyy�� MM�� dd��");
      String date = sd.format(new Date());
 
      // System.out.println(date);        
      return date;
  }
 
  /**
   * 20101214�� ������ ��¥�� �����մϴ�.
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
   * @param hhmm �ú��� ���޹޾� ����
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
      System.out.println("��¥�� ���� �� �����ϴ�.");
    } else{
      System.out.println("��� ������ ��¥�Դϴ�");
    }
  }
  
}







