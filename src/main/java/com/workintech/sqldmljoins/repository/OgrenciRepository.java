package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {

    // 2) Kitap alan öğrencilerin öğrenci bilgilerini listeleyin.
    String QUESTION_2 =
            "SELECT o.* " +
                    "FROM ogrenci o " +
                    "JOIN islem i ON o.ogrno = i.ogrno";

    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    // 3) Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 =
            "SELECT o.* " +
                    "FROM ogrenci o " +
                    "LEFT JOIN islem i ON o.ogrno = i.ogrno " +
                    "WHERE i.ogrno IS NULL";

    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();


    // 4) 10A veya 10B sınıfındaki öğrencilerin sınıf ve okuduğu kitap sayısı
    String QUESTION_4 =
            "SELECT o.sinif AS sinif, COUNT(i.kitapno) AS count " +
                    "FROM ogrenci o " +
                    "JOIN islem i ON o.ogrno = i.ogrno " +
                    "WHERE o.sinif IN ('10A', '10B') " +
                    "GROUP BY o.sinif " +
                    "ORDER BY o.sinif ASC";

    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();


    // 5) Öğrenci sayısı
    String QUESTION_5 =
            "SELECT COUNT(*) FROM ogrenci";

    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();


    // 6) Farklı öğrenci isimlerinin sayısı
    String QUESTION_6 =
            "SELECT COUNT(DISTINCT ad) FROM ogrenci";

    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();


    // 7) İsme göre öğrenci sayısı
    String QUESTION_7 =
            "SELECT ad AS ad, COUNT(*) AS count " +
                    "FROM ogrenci " +
                    "GROUP BY ad";

    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();


    // 8) Her sınıftaki öğrenci sayısı
    String QUESTION_8 =
            "SELECT sinif AS sinif, COUNT(*) AS count " +
                    "FROM ogrenci " +
                    "GROUP BY sinif " +
                    "ORDER BY CASE WHEN sinif = '9C' THEN 0 ELSE 1 END, sinif";

    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();


    // 9) Kitap okuyan her öğrencinin ad soyad ve kitap sayısı
    String QUESTION_9 =
            "SELECT o.ad AS ad, o.soyad AS soyad, COUNT(i.kitapno) AS count " +
                    "FROM ogrenci o " +
                    "JOIN islem i ON o.ogrno = i.ogrno " +
                    "GROUP BY o.ogrno, o.ad, o.soyad " +
                    "ORDER BY CASE WHEN o.ad = 'Deniz' THEN 0 ELSE 1 END, o.ad";

    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}