package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS") // DB에 order by가 예약어이기 때문에 테이블 명을 ORDERS로 보통 사용한다
public class Order {

    @Id @GeneratedValue // @GeneratedValue 만 입력하면 AUTO가 디폴트로 적용됨
    @Column(name = "ORDER_ID")
    private Long id;
    @Column(name = "MEMBER_ID")
    private Long memberId;
    private LocalDateTime orderDate; // 여기서는 편의상 구분하지 않고 LocalDateTime으로 한다
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // enum 타입 클래스를 생성할 것

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
