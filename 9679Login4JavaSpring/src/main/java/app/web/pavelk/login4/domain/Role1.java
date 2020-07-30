package app.web.pavelk.login4.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"}) // уникальность
@ToString(of = {"id", "name"})
public class Role1 implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Role1(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }


}