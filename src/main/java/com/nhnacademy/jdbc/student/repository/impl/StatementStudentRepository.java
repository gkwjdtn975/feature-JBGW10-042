package com.nhnacademy.jdbc.student.repository.impl;

import com.nhnacademy.jdbc.student.domain.Student;
import com.nhnacademy.jdbc.student.repository.StudentRepository;
import com.nhnacademy.jdbc.util.DbUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static java.time.chrono.JapaneseEra.values;

@Slf4j
public class StatementStudentRepository implements StudentRepository {

    @Override
    public int save(Student student){
        //todo#1 insert student

        String sql = String.format("insert into jdbc_students(id,name,gender,age) values('%s','%s','%s',%d)",
                student.getId(),
                student.getName(),
                student.getGender(),
                student.getAge()
        );
        log.debug("save:{}",sql);

        try(Connection connection = DbUtils.getConnection();
            Statement statement = connection.createStatement();
        ){
            int result = statement.executeUpdate(sql);
            log.debug("save:{}",result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Student> findById(String id){ // id를 기준으로 DB 테이블에서 학생 1명 정보를 조회함
        String sql = String.format("select * from jdbc_students where id='%s'",id);//String.format으로 SQL문을 만듬
        log.debug("findById:{}",sql);

        try(Connection connection = DbUtils.getConnection();//DB 연결 객체
            Statement statement = connection.createStatement(); // SQL 실행 객체
            ResultSet rs = statement.executeQuery(sql); //쿼리 결과(학생 한명의 row)를 받는 객체
        ) {
            if(rs.next()){ // rs.next() -> 결과가 있는지 확인
                Student student =  new Student(rs.getString("id"), //문자열로 id 가져옴
                        rs.getString("name"),                       // name 컬럼
                        Student.GENDER.valueOf(rs.getString("gender")), // enum 변환
                        rs.getInt("age"),                               //나이
                        rs.getTimestamp("created_at").toLocalDateTime()//시간 변환
                );
                return Optional.of(student); // 성공시 Optional로 감싸서 변환
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty(); // 결과가없으면
    }

    @Override
    public int update(Student student){
        String sql = String.format("update jdbc_students set name='%s', gender='%s', age=%d where id='%s' ",
                student.getName(),
                student.getGender(),
                student.getAge(),
                student.getId()
        );
        log.debug("update:{}",sql);

        try(Connection connection = DbUtils.getConnection();
            Statement statement = connection.createStatement();
        ) {
            int result = statement.executeUpdate(sql);
            log.debug("result:{}",result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(String id){
       //todo#4 student 삭제
    String sql = String.format("delete from jdbc_students where id='%s'",id);
    try(Connection connection = DbUtils.getConnection();
    Statement statement = connection.createStatement();
    ){
        int result = statement.executeUpdate(sql);
        log.debug("delete:{}",result);
        return result;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    }

}
