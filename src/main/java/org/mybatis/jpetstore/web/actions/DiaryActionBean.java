package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import org.mybatis.jpetstore.domain.Diary;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SessionScope
public class DiaryActionBean extends AbstractActionBean{

    private static final long serialVersionUID = 5849523372176050635L;

    private static final String WRITE_DIARY_FORM = "/WEB-INF/jsp/diary/WriteForm.jsp";

    private FileBean petImage;

    private Diary diary = new Diary();

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public FileBean getPetImage() {
        return petImage;
    }

    public void setPetImage(FileBean petImage) {
        this.petImage = petImage;
    }

    public Resolution writeDiaryForm() {
        return new ForwardResolution(WRITE_DIARY_FORM);
    }

    public Resolution addDiary() throws IOException {
        //petImage.save("image.jpg");
        String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
        String fileName = petImage.getFileName();
        int i = -1;
        i = fileName.lastIndexOf("."); // 파일 확장자 위치
        String realFileName = now + fileName.substring(i, fileName.length());  //현재시간과 확장자 합치기
        System.out.println(realFileName);
        petImage.save(new File("./" + realFileName));
        return new ForwardResolution(WRITE_DIARY_FORM);
    }
}
