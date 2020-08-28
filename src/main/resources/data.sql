insert into course_details (id, full_name, created_time, last_updated_time, is_deleted)
values(10001, 'JPA in 50 Steps', sysdate(), sysdate(), false);
insert into course_details (id, full_name, created_time, last_updated_time, is_deleted)
values(10002, 'Spring in 50 Steps', sysdate(), sysdate(), false);
insert into course_details (id, full_name, created_time, last_updated_time, is_deleted)
values(10003, 'SpringBoot in 100 Steps', sysdate(), sysdate(), false);
insert into course_details (id, full_name, created_time, last_updated_time, is_deleted)
values(10004, 'Hibernate in 150 Steps', sysdate(), sysdate(), false);
insert into course_details (id, full_name, created_time, last_updated_time, is_deleted)
values(10005, 'Hibernate in 150 Steps', sysdate(), sysdate(), false);
insert into course_details (id, full_name, created_time, last_updated_time, is_deleted)
values(10006, 'Dummy1', sysdate(), sysdate(), false);
insert into course_details (id, full_name, created_time, last_updated_time, is_deleted)
values(10007, 'Dummy2', sysdate(), sysdate(), false);
insert into course_details (id, full_name, created_time, last_updated_time, is_deleted)
values(10008, 'Dummy3', sysdate(), sysdate(), false);
insert into course_details (id, full_name, created_time, last_updated_time, is_deleted)
values(10009, 'Dummy4', sysdate(), sysdate(), false);
insert into course_details (id, full_name, created_time, last_updated_time, is_deleted)
values(10010, 'Dummy5', sysdate(), sysdate(), false);

insert into passport (id, number) values(40001, 'E123456');
insert into passport (id, number) values(40002, 'F123456');
insert into passport (id, number) values(40003, 'X456789');
insert into passport (id, number) values(40004, 'A243546');

insert into student (id, name, passport_id) values(20001, 'Gene', 40001);
insert into student (id, name, passport_id) values(20002, 'Adam', 40002);
insert into student (id, name, passport_id) values(20003, 'Jane', 40003);
insert into student (id, name, passport_id) values(20004, 'Yevhen', 40004);

insert into review (id, rating, description, course_id) values(50001, 'FIVE', 'Grate Course', 10001);
insert into review (id, rating, description, course_id) values(50002, 'FOUR', 'Wonderful Course', 10001);
insert into review (id, rating, description, course_id) values(50003, 'FIVE', 'Awesome Course', 10002);
insert into review (id, rating, description, course_id) values(50004, 'FOUR', 'Grate Course', 10003);
insert into review (id, rating, description, course_id) values(50005, 'FOUR', 'Grate Course', 10003);
insert into review (id, rating, description, course_id) values(50006, 'FIVE', 'Grate Course', 10004);

insert into student_course(student_id, course_id) values(20001, 10001);
insert into student_course(student_id, course_id) values(20002, 10001);
insert into student_course(student_id, course_id) values(20003, 10001);
insert into student_course(student_id, course_id) values(20001, 10003);