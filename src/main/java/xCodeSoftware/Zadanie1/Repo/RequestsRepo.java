package xCodeSoftware.Zadanie1.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xCodeSoftware.Zadanie1.Classes.request;

import java.util.ArrayList;

@Repository
public interface RequestsRepo extends JpaRepository<request, Long> {

}
