package com.example.New.shop.repo;

import com.example.New.shop.entities.Bucket;
import com.example.New.shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BucketRepository extends CrudRepository<Bucket, Long> {
}
