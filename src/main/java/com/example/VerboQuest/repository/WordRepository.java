package com.example.VerboQuest.repository;

import com.example.VerboQuest.pojo.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends CrudRepository<Word, Integer> {
}
