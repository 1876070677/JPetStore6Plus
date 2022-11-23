package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.Diary;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.mapper.DiaryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiaryService {
    private final DiaryMapper diaryMapper;

    public DiaryService(DiaryMapper diaryMapper) {
        this.diaryMapper = diaryMapper;
    }

    public void addDiary(Diary diary) {
        diaryMapper.addDiary(diary);
    }
}
