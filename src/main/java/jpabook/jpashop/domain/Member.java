package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    
    @Id @GeneratedValue // @GeneratedValue 만 입력하면 AUTO가 디폴트로 적용됨
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String city; // 자동 DDL은 varchar(255)로 문자열을 잡는다. 길이를 변경하려면 @Column(length = ) 설정하거나, SQL문을 직접 수정한다
    private String street; // 개발자들은 보통 SQL을 만지는 것보다 메타데이터(어노테이션)를 코드에 넣는 것을 더 선호한다. 안 그러면 DB 테이블 다 체크해야 함.
    private String zipcode; // ex) @Column(length = 10), 유니크 제약조건이나 인덱스도 @Table(indexes = ) 이렇게 직접 코드에 넣는 것이 좋다 
                            // 그래야 나중에 JPQL 쿼리 짤 때 이 어노테이션을 보고 손쉽게 코딩할 수 있는 것이다
    // Getter는 모두 만들어 주는데, Setter는 고민의 여지가 있다. 지금은 예제니 모두 만들어준다

    // 아래 양방향 설정은 실습이라서 해 본 것. 실무에서는 Member에서 order 조회 자체가 비즈니스 로직이라고 보기 어려움. 아주 안 좋은 코드임.
    @OneToMany(mappedBy = "member") // 연관관계 주인 설정
    private List<Order> orders = new ArrayList<>(); // 관례) null point exception 방지를 위하여 생성하여 초기화한다
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
