package may.i.jhq.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jinhuaquan
 * @create 2018-01-18 上午9:11
 * @desc The domain of user
 **/
@Data
@Entity(name = "m_user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 19950620;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 32, nullable = true)
    private String name;

    @Column(name = "age", nullable = true, length = 3)
    private Integer age;

    @Column(name = "email", length = 16)
    private String email;

    @Column(name = "create_time")
    private Date createTime;

    @OneToMany
    @JoinColumn(name = "uid")
    private List<Order> orders;
}
