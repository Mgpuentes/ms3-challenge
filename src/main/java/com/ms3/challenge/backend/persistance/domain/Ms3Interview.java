package com.ms3.challenge.backend.persistance.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Matthew Puentes on 12/5/19
 */

@Entity
@Data
public class Ms3Interview implements Serializable {

    /** The Serial Version UID for Serializable classes **/
    private static final long serialVersionUID = 1395998666112603489L;

    @GenericGenerator(
            name = "sequenceStyleGenerator",
            strategy = "org.hibernate.id.enhanced.sequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1"),
                    @Parameter(name = "sequence_name", value = "sequence")
            }
    )

    @Id
    @GeneratedValue(generator = "sequenceStyleGenerator")
    private long id;

    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;
}
