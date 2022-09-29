package com.hertzbit.restserver.rest.service;

import com.hertzbit.restserver.dao.StudentDB;
import com.hertzbit.restserver.rest.domain.Student;
import com.hertzbit.restserver.rest.domain.Students;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/students")
@Produces("application/xml")
public class StudentResource {

    static {
        Student studentOne = new Student();
        studentOne.setStudentFirstName("John");
        studentOne.setStudentLastName("Doe");

        Student studentTwo = new Student();
        studentTwo.setStudentFirstName("Bruce");
        studentTwo.setStudentLastName("Wayne");

        StudentDB.createStudent(studentOne);
        StudentDB.createStudent(studentTwo);
    }

    @GET
    @Produces("application/xml")
    public Students getStudents() {

        List<Student> studentList = StudentDB.getAllStudent();
        Students allStudents = new Students();
        allStudents.setSize(studentList.size());
        allStudents.setStudents(studentList);
        return allStudents;
    }

    @GET
    @Produces("application/xml")
    @Path("/{studentId}")
    public Response getStudentById(@PathParam("studentId") Integer studentId) {

        Student studentFromDB = StudentDB.getStudentById(studentId);
        if (studentFromDB == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(studentFromDB).build();
    }

    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    public Response createStudent(Student studentRequest) {

        if(studentRequest == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Request Body is Empty")
                    .build();
        }

        Student savedStudent = StudentDB.createStudent(studentRequest);
        return Response.status(Response.Status.CREATED)
                .entity(savedStudent).build();
    }

    @PUT
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/{studentId}")
    public Response updateStudent(@PathParam("studentId") Integer studentId,
                              Student studentRequest) {

        Student studentFromDB = StudentDB.getStudentById(studentId);
        if (studentFromDB == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(studentRequest == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Request Body is Empty")
                    .build();
        }

        Student updatedStudent = StudentDB.updateStudentById(studentId, studentRequest);
        return Response.status(Response.Status.OK)
                .entity(updatedStudent).build();
    }

    @DELETE
    @Produces("application/xml")
    @Path("/{studentId}")
    public Response deleteStudent(@PathParam("studentId") Integer studentId) {

        Student studentFromDB = StudentDB.getStudentById(studentId);
        if (studentFromDB == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        StudentDB.removeStudentById(studentId);
        studentFromDB = StudentDB.getStudentById(studentId);
        if (studentFromDB == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
