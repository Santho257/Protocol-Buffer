package com.santho.repo;

import com.santho.proto.CollegeProto;

import java.io.IOException;

public interface StudentRepo {
    void add(CollegeProto.Student student) throws IOException;
    void remove(String regNumber) throws IOException;

    CollegeProto.Student[] getStudents() throws IOException;
}
