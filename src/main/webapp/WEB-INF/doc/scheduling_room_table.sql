DROP TABLE room;
DROP TABLE roomuse;
DROP TABLE room_reservation;
DROP TABLE SCHEDULE;

/********************* FK용 임시 사원 테이블 시작 *********************/

/**********************************/
/*     Table Name: 사원  */
/**********************************/
CREATE TABLE Employee(
employeeno          NUMBER(10)    NOT NULL,
employee_name         VARCHAR(10)     NOT NULL,
department_name         VARCHAR(10)     NOT NULL,
PRIMARY KEY(employeeno)
);
COMMENT ON TABLE Employee is '사원';
COMMENT ON COLUMN Employee.employeeno is '사원번호';
COMMENT ON COLUMN Employee.employee_name is '사원이름';
COMMENT ON COLUMN Employee.departmentno is '부서이름';

/* 사원 등록 */
INSERT INTO Employee(employeeno, employee_name, department_name)
VALUES((select NVL(max(employeeno),0)+1 as employeeno from Employee), '마스터', '관리팀');

INSERT INTO Employee(employeeno, employee_name, department_name)
VALUES((select NVL(max(employeeno),0)+1 as employeeno from Employee), '왕눈이', '개발팀');

INSERT INTO Employee(employeeno, employee_name, department_name)
VALUES((select NVL(max(employeeno),0)+1 as employeeno from Employee), '아로미', '설계팀');

/* 사원 목록 */
SELECT employeeno, employee_name, department_name
FROM Employee;

/* 사원 조회 */
SELECT employeeno, employee_name, department_name
FROM Employee
WHERE employeeno=1;

/********************* FK용 임시 사원 테이블 종료 *********************/

/********************* 회의실 테이블 시작 *********************/

/************************************************/
/*     Table Name: 회의실 이용 내역   */
/***********************************************/

CREATE TABLE room(
roomno INT NOT NULL,
roomname VARCHAR(30) NOT NULL,
location VARCHAR(50),
PRIMARY KEY(roomno)
);

COMMENT ON TABLE room is '회의실';
COMMENT ON COLUMN room.roomno is '회의실 번호';
COMMENT ON COLUMN room.roomname is '회의실 이름';
COMMENT ON COLUMN room.location is '회의실 위치';

/* 회의실 등록 */
INSERT INTO room(roomno, roomname, location)
VALUES((SELECT NVL(MAX(roomno), 0)+1 as roomno FROM room), '2층 회의실', '2층 중앙 엘리베이터 앞');

INSERT INTO room(roomno, roomname, location)
VALUES((SELECT NVL(MAX(roomno), 0)+1 as roomno FROM room), '3층 회의실-1', '3층 탕비실 앞');

INSERT INTO room(roomno, roomname, location)
VALUES((SELECT NVL(MAX(roomno), 0)+1 as roomno FROM room), '3층 회의실-2', '3층 화물 엘리베이터 앞');

/* 회의실 목록 */
SELECT roomno, roomname, location
FROM room;

/* 회의실 조회 */
SELECT roomno, roomname, location
FROM room
WHERE roomno = 1;

/* 회의실 수정 */
-- 레코드 수정
UPDATE room
SET roomname='3층 대회의실'
WHERE roomno = 2;

UPDATE room
SET roomname = '3층 소회의실'
WHERE roomno = 3;

/* 회의실 삭제 */
DELETE FROM room
WHERE roomno = 1;

/********************* 회의실 테이블 종료 *********************/

/********************* 회의실 이용 내역 테이블 시작 *********************/

/**********************************/
/*     Table Name: 회의실   */
/**********************************/

CREATE TABLE roomuse(
roomuseno INT NOT NULL PRIMARY KEY,
roomno INT NOT NULL,
employeeno INT NOT NULL,
roomuse VARCHAR(50),
roomusedate VARCHAR(20),
start_time VARCHAR(10),
end_time VARCHAR(10),
FOREIGN KEY (roomno) REFERENCES room(roomno),
FOREIGN KEY (employeeno) REFERENCES employee(employeeno)
);

COMMENT ON TABLE roomuse is '회의실 이용 내역';
COMMENT ON COLUMN roomuse.roomuseno is '회의실 이용 내역 번호';
COMMENT ON COLUMN roomuse.roomno is '회의실 번호';
COMMENT ON COLUMN roomuse.employeeno is '사원 번호';
COMMENT ON COLUMN roomuse.roomuse is '회의실 이용 일정';
COMMENT ON COLUMN roomuse.roomusedate is '회의실 이용 날짜';
COMMENT ON COLUMN roomuse.start_time is '회의실 이용 시작 시간';
COMMENT ON COLUMN roomuse.end_time is '회의실 이용 종료 시간';

/* 회의실 이용 내역 등록 */
INSERT INTO roomuse(roomuseno, roomno, employeeno, roomuse, roomusedate, start_time, end_time)
VALUES ((SELECT NVL(MAX(roomuseno), 0)+1 as roomuseno FROM roomuse), 1, 1, '인사과 회의', '2018-12-11', '14:00', '16:00');

INSERT INTO roomuse(roomuseno, roomno, employeeno, roomuse, roomusedate, start_time, end_time)
VALUES ((SELECT NVL(MAX(roomuseno), 0)+1 as roomuseno FROM roomuse), 2, 3, '프로젝트 회의1', '2018-12-12', '10:00', '11:00');

INSERT INTO roomuse(roomuseno, roomno, employeeno, roomuse, roomusedate, start_time, end_time)
VALUES ((SELECT NVL(MAX(roomuseno), 0)+1 as roomuseno FROM roomuse), 1, 2, '프로젝트 회의2', '2018-12-12', '11:00', '12:30');

/* 회의실 이용 내역 목록 */
SELECT roomuseno, roomno, employeeno, roomuse, roomusedate, start_time, end_time
FROM roomuse;

/* 회의실 이용 내역 조회 */
SELECT roomuseno, roomno, employeeno, roomuse, roomusedate, start_time, end_time
FROM roomuse
WHERE roomuseno = 1;

/* 회의실 이용 내역 수정 */
UPDATE roomuse
SET roomno = 1, roomuse = '프로젝트 개발 현황 확인', roomusedate = '2018-12-12', start_time = '11:00', end_time = '12:00'
WHERE roomuseno = 2;

/* 회의실 이용 내역 삭제 */
DELETE FROM roomuse
WHERE roomuseno = 3;

/********************* 회의실 이용 내역 테이블 종료 *********************/

/********************* 회의실 예약 테이블 시작 *********************/

/*****************************************/
/*     Table Name: 회의실 예약  */
/****************************************/

CREATE TABLE room_reservation(
reservationno INT NOT NULL PRIMARY KEY,
roomno INT NOT NULL,
employeeno INT NOT NULL,
reservation VARCHAR(50),
reserv_date VARCHAR(20),
start_time VARCHAR(10),
end_time VARCHAR(10),
FOREIGN KEY (roomno) REFERENCES room(roomno),
FOREIGN KEY (employeeno) REFERENCES employee(employeeno)
);

COMMENT ON TABLE room_reservation is '회의실 예약';
COMMENT ON COLUMN room_reservation.reservationno is '회의실 예약 번호';
COMMENT ON COLUMN room_reservation.roomno is '회의실 번호';
COMMENT ON COLUMN room_reservation.employeeno is '사원 번호';
COMMENT ON COLUMN room_reservation.reservation is '회의실 예약 일정';
COMMENT ON COLUMN room_reservation.reserv_date is '회의실 예약 날짜';
COMMENT ON COLUMN room_reservation.start_time is '회의실 예약 시작 시간';
COMMENT ON COLUMN room_reservation.end_time is '회의실 예약 종료 시간';

/* 회의실 예약 등록 */
INSERT INTO room_reservation(reservationno, roomno, employeeno, reservation, reserv_date, start_time, end_time)
VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM room_reservation), 1, 1, '프로젝트1 회의', '2018-12-13', '11:00', '12:00');

INSERT INTO room_reservation(reservationno, roomno, employeeno, reservation, reserv_date, start_time, end_time)
VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM room_reservation), 2, 3,  '프로젝트2 회의', '2018-12-12', '09:00', '11:00');

INSERT INTO room_reservation(reservationno, roomno, employeeno, reservation, reserv_date, start_time, end_time)
VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM room_reservation), 1, 2,  '프로젝트3 회의', '2018-12-13', '11:00', '12:00');

INSERT INTO room_reservation(reservationno, roomno, employeeno, reservation, reserv_date, start_time, end_time)
VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM room_reservation), 1, 2,  '프로젝트4 회의', '2018-12-11', '15:00', '16:00');

/* 회의실 예약 목록 */
SELECT reservationno, roomno, employeeno, reservation, reserv_date, start_time, end_time
FROM room_reservation;

/* 회의실 예약 조회 */
SELECT reservationno, roomno, employeeno, reservation, reserv_date, start_time, end_time
FROM room_reservation
WHERE reservationno = 1;

/* 회의실 예약 수정 */
UPDATE room_reservation
SET roomno = 3, reservation = '프로젝트5 회의', reserv_date = '2018-12-12', start_time = '14:00', end_time = '16:00'
WHERE reservationno = 1;

/* 회의실 예약 삭제 */
DELETE FROM room_reservation
WHERE reservationno = 3;

/* 회의실 예약 중복 확인 */
SELECT COUNT(*) as cnt
FROM (
    SELECT re.reservationno, use.roomuseno, re.reserv_date, re.start_time, re.end_time
    FROM room_reservation re, roomuse use
    WHERE use.start_time <= re.start_time AND use.end_time > re.start_time
    AND (re.reserv_date = use.roomusedate) AND (re.roomno = use.roomno)
);

/********************* 회의실 예약 테이블 시작 *********************/

/********************* 스케줄링 테이블 시작 *********************/

/**********************************/
/*     Table Name: 일정  */
/**********************************/
DROP TABLE SCHEDULE;

CREATE TABLE SCHEDULE(
scheduleno INT NOT NULL PRIMARY KEY,
employeeno INT NOT NULL,
work VARCHAR(100) ,
work_startdate VARCHAR(20) ,
work_enddate VARCHAR(20) ,
start_time VARCHAR(10),
end_time VARCHAR(10),
visible VARCHAR(10),
FOREIGN KEY (employeeno) REFERENCES employee(employeeno)
)

COMMENT ON TABLE SCHEDULE is '일정';
COMMENT ON COLUMN SCHEDULE.scheduleno is '일정 번호';
COMMENT ON COLUMN SCHEDULE.employeeno is '사원 번호';
COMMENT ON COLUMN SCHEDULE.work is '일정';
COMMENT ON COLUMN SCHEDULE.work_startdate is '일정 시작 날짜';
COMMENT ON COLUMN SCHEDULE.work_enddate is '일정 종료 날짜';
COMMENT ON COLUMN SCHEDULE.start_time is '일정 시작 시간';
COMMENT ON COLUMN SCHEDULE.end_time is '일정 종료 시간';
COMMENT ON COLUMN SCHEDULE.visible is '공개 여부';

/* 일정 등록 */
INSERT INTO SCHEDULE(scheduleno, employeeno, work, work_startdate, work_enddate, start_time, end_time, visible)
VALUES ((SELECT NVL(MAX(scheduleno), 0)+1 as scheduleno FROM SCHEDULE), 1, '출장', '2018-12-12', '2018-12-14', '7:00', '6:00', 'Y');

INSERT INTO SCHEDULE(scheduleno, employeeno, work, work_startdate, work_enddate, start_time, end_time, visible)
VALUES ((SELECT NVL(MAX(scheduleno), 0)+1 as scheduleno FROM SCHEDULE), 1, '오전 반차', '2018-12-17', '2018-12-17', '8:00', '12:00', 'N');

INSERT INTO SCHEDULE(scheduleno, employeeno, work, work_startdate, work_enddate, start_time, end_time, visible)
VALUES ((SELECT NVL(MAX(scheduleno), 0)+1 as scheduleno FROM SCHEDULE), 1, '세미나 참석', '2018-12-27', '2018-12-28', '9:00', '18:00', 'N');

/* 일정 목록 */
SELECT scheduleno, employeeno, work, work_startdate, work_enddate, start_time, end_time, visible
FROM SCHEDULE
ORDER BY work_startdate ASC;

/* 일정 조회 */
SELECT scheduleno, employeeno, work, work_startdate, work_enddate, start_time, end_time, visible
FROM SCHEDULE
WHERE scheduleno = 11;

/* 일정 수정 */
UPDATE SCHEDULE
SET employeeno = 1, work = '오후 반차', work_startdate = '2018-12-17', work_enddate= '2018-12-17',
     start_time = '13:00', end_time = '18:00', visible = '비공개'
WHERE scheduleno = 2;

/* 일정 삭제 */
DELETE FROM SCHEDULE
WHERE scheduleno = 3;


/********************* 스케줄링 테이블 종료 *********************/

SELECT scheduleno, employeeno, work, work_startdate, work_enddate, start_time, end_time, visible
    FROM SCHEDULE
    WHERE scheduleno = 1;
