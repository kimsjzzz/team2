DROP TABLE room;
DROP TABLE roomuse;
DROP TABLE room_reservation;
DROP TABLE SCHEDULE;

/********************* FK�� �ӽ� ��� ���̺� ���� *********************/

/**********************************/
/*     Table Name: ���  */
/**********************************/
CREATE TABLE Employee(
employeeno          NUMBER(10)    NOT NULL,
employee_name         VARCHAR(10)     NOT NULL,
department_name         VARCHAR(10)     NOT NULL,
PRIMARY KEY(employeeno)
);
COMMENT ON TABLE Employee is '���';
COMMENT ON COLUMN Employee.employeeno is '�����ȣ';
COMMENT ON COLUMN Employee.employee_name is '����̸�';
COMMENT ON COLUMN Employee.departmentno is '�μ��̸�';

/* ��� ��� */
INSERT INTO Employee(employeeno, employee_name, department_name)
VALUES((select NVL(max(employeeno),0)+1 as employeeno from Employee), '������', '������');

INSERT INTO Employee(employeeno, employee_name, department_name)
VALUES((select NVL(max(employeeno),0)+1 as employeeno from Employee), '�մ���', '������');

INSERT INTO Employee(employeeno, employee_name, department_name)
VALUES((select NVL(max(employeeno),0)+1 as employeeno from Employee), '�Ʒι�', '������');

/* ��� ��� */
SELECT employeeno, employee_name, department_name
FROM Employee;

/* ��� ��ȸ */
SELECT employeeno, employee_name, department_name
FROM Employee
WHERE employeeno=1;

/********************* FK�� �ӽ� ��� ���̺� ���� *********************/

/********************* ȸ�ǽ� ���̺� ���� *********************/

/************************************************/
/*     Table Name: ȸ�ǽ� �̿� ����   */
/***********************************************/

CREATE TABLE room(
roomno INT NOT NULL,
roomname VARCHAR(30) NOT NULL,
location VARCHAR(50),
PRIMARY KEY(roomno)
);

COMMENT ON TABLE room is 'ȸ�ǽ�';
COMMENT ON COLUMN room.roomno is 'ȸ�ǽ� ��ȣ';
COMMENT ON COLUMN room.roomname is 'ȸ�ǽ� �̸�';
COMMENT ON COLUMN room.location is 'ȸ�ǽ� ��ġ';

/* ȸ�ǽ� ��� */
INSERT INTO room(roomno, roomname, location)
VALUES((SELECT NVL(MAX(roomno), 0)+1 as roomno FROM room), '2�� ȸ�ǽ�', '2�� �߾� ���������� ��');

INSERT INTO room(roomno, roomname, location)
VALUES((SELECT NVL(MAX(roomno), 0)+1 as roomno FROM room), '3�� ȸ�ǽ�-1', '3�� ����� ��');

INSERT INTO room(roomno, roomname, location)
VALUES((SELECT NVL(MAX(roomno), 0)+1 as roomno FROM room), '3�� ȸ�ǽ�-2', '3�� ȭ�� ���������� ��');

/* ȸ�ǽ� ��� */
SELECT roomno, roomname, location
FROM room;

/* ȸ�ǽ� ��ȸ */
SELECT roomno, roomname, location
FROM room
WHERE roomno = 1;

/* ȸ�ǽ� ���� */
-- ���ڵ� ����
UPDATE room
SET roomname='3�� ��ȸ�ǽ�'
WHERE roomno = 2;

UPDATE room
SET roomname = '3�� ��ȸ�ǽ�'
WHERE roomno = 3;

/* ȸ�ǽ� ���� */
DELETE FROM room
WHERE roomno = 1;

/********************* ȸ�ǽ� ���̺� ���� *********************/

/********************* ȸ�ǽ� �̿� ���� ���̺� ���� *********************/

/**********************************/
/*     Table Name: ȸ�ǽ�   */
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

COMMENT ON TABLE roomuse is 'ȸ�ǽ� �̿� ����';
COMMENT ON COLUMN roomuse.roomuseno is 'ȸ�ǽ� �̿� ���� ��ȣ';
COMMENT ON COLUMN roomuse.roomno is 'ȸ�ǽ� ��ȣ';
COMMENT ON COLUMN roomuse.employeeno is '��� ��ȣ';
COMMENT ON COLUMN roomuse.roomuse is 'ȸ�ǽ� �̿� ����';
COMMENT ON COLUMN roomuse.roomusedate is 'ȸ�ǽ� �̿� ��¥';
COMMENT ON COLUMN roomuse.start_time is 'ȸ�ǽ� �̿� ���� �ð�';
COMMENT ON COLUMN roomuse.end_time is 'ȸ�ǽ� �̿� ���� �ð�';

/* ȸ�ǽ� �̿� ���� ��� */
INSERT INTO roomuse(roomuseno, roomno, employeeno, roomuse, roomusedate, start_time, end_time)
VALUES ((SELECT NVL(MAX(roomuseno), 0)+1 as roomuseno FROM roomuse), 1, 1, '�λ�� ȸ��', '2018-12-11', '14:00', '16:00');

INSERT INTO roomuse(roomuseno, roomno, employeeno, roomuse, roomusedate, start_time, end_time)
VALUES ((SELECT NVL(MAX(roomuseno), 0)+1 as roomuseno FROM roomuse), 2, 3, '������Ʈ ȸ��1', '2018-12-12', '10:00', '11:00');

INSERT INTO roomuse(roomuseno, roomno, employeeno, roomuse, roomusedate, start_time, end_time)
VALUES ((SELECT NVL(MAX(roomuseno), 0)+1 as roomuseno FROM roomuse), 1, 2, '������Ʈ ȸ��2', '2018-12-12', '11:00', '12:30');

/* ȸ�ǽ� �̿� ���� ��� */
SELECT roomuseno, roomno, employeeno, roomuse, roomusedate, start_time, end_time
FROM roomuse;

/* ȸ�ǽ� �̿� ���� ��ȸ */
SELECT roomuseno, roomno, employeeno, roomuse, roomusedate, start_time, end_time
FROM roomuse
WHERE roomuseno = 1;

/* ȸ�ǽ� �̿� ���� ���� */
UPDATE roomuse
SET roomno = 1, roomuse = '������Ʈ ���� ��Ȳ Ȯ��', roomusedate = '2018-12-12', start_time = '11:00', end_time = '12:00'
WHERE roomuseno = 2;

/* ȸ�ǽ� �̿� ���� ���� */
DELETE FROM roomuse
WHERE roomuseno = 3;

/********************* ȸ�ǽ� �̿� ���� ���̺� ���� *********************/

/********************* ȸ�ǽ� ���� ���̺� ���� *********************/

/*****************************************/
/*     Table Name: ȸ�ǽ� ����  */
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

COMMENT ON TABLE room_reservation is 'ȸ�ǽ� ����';
COMMENT ON COLUMN room_reservation.reservationno is 'ȸ�ǽ� ���� ��ȣ';
COMMENT ON COLUMN room_reservation.roomno is 'ȸ�ǽ� ��ȣ';
COMMENT ON COLUMN room_reservation.employeeno is '��� ��ȣ';
COMMENT ON COLUMN room_reservation.reservation is 'ȸ�ǽ� ���� ����';
COMMENT ON COLUMN room_reservation.reserv_date is 'ȸ�ǽ� ���� ��¥';
COMMENT ON COLUMN room_reservation.start_time is 'ȸ�ǽ� ���� ���� �ð�';
COMMENT ON COLUMN room_reservation.end_time is 'ȸ�ǽ� ���� ���� �ð�';

/* ȸ�ǽ� ���� ��� */
INSERT INTO room_reservation(reservationno, roomno, employeeno, reservation, reserv_date, start_time, end_time)
VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM room_reservation), 1, 1, '������Ʈ1 ȸ��', '2018-12-13', '11:00', '12:00');

INSERT INTO room_reservation(reservationno, roomno, employeeno, reservation, reserv_date, start_time, end_time)
VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM room_reservation), 2, 3,  '������Ʈ2 ȸ��', '2018-12-12', '09:00', '11:00');

INSERT INTO room_reservation(reservationno, roomno, employeeno, reservation, reserv_date, start_time, end_time)
VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM room_reservation), 1, 2,  '������Ʈ3 ȸ��', '2018-12-13', '11:00', '12:00');

INSERT INTO room_reservation(reservationno, roomno, employeeno, reservation, reserv_date, start_time, end_time)
VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM room_reservation), 1, 2,  '������Ʈ4 ȸ��', '2018-12-11', '15:00', '16:00');

/* ȸ�ǽ� ���� ��� */
SELECT reservationno, roomno, employeeno, reservation, reserv_date, start_time, end_time
FROM room_reservation;

/* ȸ�ǽ� ���� ��ȸ */
SELECT reservationno, roomno, employeeno, reservation, reserv_date, start_time, end_time
FROM room_reservation
WHERE reservationno = 1;

/* ȸ�ǽ� ���� ���� */
UPDATE room_reservation
SET roomno = 3, reservation = '������Ʈ5 ȸ��', reserv_date = '2018-12-12', start_time = '14:00', end_time = '16:00'
WHERE reservationno = 1;

/* ȸ�ǽ� ���� ���� */
DELETE FROM room_reservation
WHERE reservationno = 3;

/* ȸ�ǽ� ���� �ߺ� Ȯ�� */
SELECT COUNT(*) as cnt
FROM (
    SELECT re.reservationno, use.roomuseno, re.reserv_date, re.start_time, re.end_time
    FROM room_reservation re, roomuse use
    WHERE use.start_time <= re.start_time AND use.end_time > re.start_time
    AND (re.reserv_date = use.roomusedate) AND (re.roomno = use.roomno)
);

/********************* ȸ�ǽ� ���� ���̺� ���� *********************/

/********************* �����ٸ� ���̺� ���� *********************/

/**********************************/
/*     Table Name: ����  */
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

COMMENT ON TABLE SCHEDULE is '����';
COMMENT ON COLUMN SCHEDULE.scheduleno is '���� ��ȣ';
COMMENT ON COLUMN SCHEDULE.employeeno is '��� ��ȣ';
COMMENT ON COLUMN SCHEDULE.work is '����';
COMMENT ON COLUMN SCHEDULE.work_startdate is '���� ���� ��¥';
COMMENT ON COLUMN SCHEDULE.work_enddate is '���� ���� ��¥';
COMMENT ON COLUMN SCHEDULE.start_time is '���� ���� �ð�';
COMMENT ON COLUMN SCHEDULE.end_time is '���� ���� �ð�';
COMMENT ON COLUMN SCHEDULE.visible is '���� ����';

/* ���� ��� */
INSERT INTO SCHEDULE(scheduleno, employeeno, work, work_startdate, work_enddate, start_time, end_time, visible)
VALUES ((SELECT NVL(MAX(scheduleno), 0)+1 as scheduleno FROM SCHEDULE), 1, '����', '2018-12-12', '2018-12-14', '7:00', '6:00', 'Y');

INSERT INTO SCHEDULE(scheduleno, employeeno, work, work_startdate, work_enddate, start_time, end_time, visible)
VALUES ((SELECT NVL(MAX(scheduleno), 0)+1 as scheduleno FROM SCHEDULE), 1, '���� ����', '2018-12-17', '2018-12-17', '8:00', '12:00', 'N');

INSERT INTO SCHEDULE(scheduleno, employeeno, work, work_startdate, work_enddate, start_time, end_time, visible)
VALUES ((SELECT NVL(MAX(scheduleno), 0)+1 as scheduleno FROM SCHEDULE), 1, '���̳� ����', '2018-12-27', '2018-12-28', '9:00', '18:00', 'N');

/* ���� ��� */
SELECT scheduleno, employeeno, work, work_startdate, work_enddate, start_time, end_time, visible
FROM SCHEDULE
ORDER BY work_startdate ASC;

/* ���� ��ȸ */
SELECT scheduleno, employeeno, work, work_startdate, work_enddate, start_time, end_time, visible
FROM SCHEDULE
WHERE scheduleno = 11;

/* ���� ���� */
UPDATE SCHEDULE
SET employeeno = 1, work = '���� ����', work_startdate = '2018-12-17', work_enddate= '2018-12-17',
     start_time = '13:00', end_time = '18:00', visible = '�����'
WHERE scheduleno = 2;

/* ���� ���� */
DELETE FROM SCHEDULE
WHERE scheduleno = 3;


/********************* �����ٸ� ���̺� ���� *********************/

SELECT scheduleno, employeeno, work, work_startdate, work_enddate, start_time, end_time, visible
    FROM SCHEDULE
    WHERE scheduleno = 1;
