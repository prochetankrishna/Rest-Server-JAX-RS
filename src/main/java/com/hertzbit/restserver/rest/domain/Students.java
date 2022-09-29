package com.hertzbit.restserver.rest.domain;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "students")
@XmlAccessorType(XmlAccessType.FIELD)
public class Students {

    @XmlAttribute
    private Integer size;

    @XmlElement(name = "student")
    private List<Student> students;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
