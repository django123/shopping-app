package com.django.it.shoppingapp.repositories;

import com.django.it.shoppingapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("select t from  Task as t join t.shopping ts where ts.id =: id order by ts.id desc")
    List<Task> findAllByShoppingOrderByTaskIdDesc(@Param("id") Integer id);
}
