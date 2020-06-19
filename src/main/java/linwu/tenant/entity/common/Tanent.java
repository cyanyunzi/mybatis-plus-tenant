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
public class Tanent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;


}
