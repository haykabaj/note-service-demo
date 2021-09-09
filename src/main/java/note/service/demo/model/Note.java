package note.service.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Max;

import java.util.Date;

@Entity(name = "notes")
public class Note {
    public Note(){}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;


    @Column(name = "note")
//    @Max(value = 1, message = "note size should not be greater than 1000")
    private String note;

    @Column(name = "created_at" )
    private Date createTime;

    @Column(name = "updated_at")
    private Date lastUpdateTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
