package may.i.jhq.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author jinhuaquan
 * @create 2018-01-18 下午1:35
 * @desc The domain of order
 **/
@Data
@Entity(name = "m_order")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable{

    private static final long serialVersionUID = 19950620;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(name = "uid")
    private Long uId;

    private BigDecimal total;

    /**
     *  实体映射重复列必须设置：insertable = false,updatable = false
     */
    @OneToOne
    @JoinColumn(name = "uid", insertable = false, updatable = false)
    private User user;

}
