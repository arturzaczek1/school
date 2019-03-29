package pl.arturzaczek.school.entitis;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "post_title")
    private String postTitle;
    @Column (name = "post_body")
    private String postBody;
    @OneToOne
    @JoinColumn (name = "post_author")
    private User postAuthor;
    @Column (name = "added_date")
    private LocalDateTime addedDate;
}
