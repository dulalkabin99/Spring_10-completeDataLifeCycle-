package com.dulal.spring10_complete_data_life_cycle;


import org.springframework.data.repository.CrudRepository;

public interface CourseRepo extends CrudRepository<Course,Long> {
}
