package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Account;
import org.mybatis.jpetstore.domain.Diary;

import java.util.List;

public interface DiaryMapper {
    void addDiary(Diary diary);
    int getTotal();
    List<Diary> getDiaryList(int offset);
}
