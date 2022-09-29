package com.hertzbit.restserver.dao;

import com.hertzbit.restserver.rest.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentDB {

    private static Map<Integer, Student> studentMap = new ConcurrentHashMap<>();
    private static AtomicInteger studentIdGenerator = new AtomicInteger();

    public static Student getStudentById(Integer studentId) {
        return studentMap.get(studentId);
    }

    public static List<Student> getAllStudent() {
        return new ArrayList<Student>(studentMap.values());
    }

    public static Student removeStudentById(Integer studentId) {
        return studentMap.remove(studentId);
    }

    public static Student updateStudentById(Integer studentId, Student studentRequest) {
        return studentMap.put(studentId, studentRequest);
    }

    public static Student createStudent(Student studentRequest) {
        studentRequest.setStudentId(studentIdGenerator.incrementAndGet());
        studentMap.put(studentRequest.getStudentId(), studentRequest);
        return studentRequest;
    }
}
