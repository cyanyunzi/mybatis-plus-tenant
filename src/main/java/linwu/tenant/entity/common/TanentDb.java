package linwu.tenant.entity.common;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author linwu
 * @since 2020-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TanentDb implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 数据库名
     */
    private String databse;

    /**
     * 代理商ID
     */
    private Integer tanentId;

    /**
     * 数据库帐号
     */
    private String dbAccount;

    /**
     * 数据库密码
     */
    private String dbPassword;


}
