package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS") // DB에 order by가 예약어이기 때문에 테이블 명을 ORDERS로 보통 사용한다
public class Order {

    @Id @GeneratedValue // @GeneratedValue 만 입력하면 AUTO가 디폴트로 적용됨
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID") // @Column() , @JoinColumn() <-- 동시에 쓰는 것이 아니고 선택하여 쓰는 것
    private Member member;

    @OneToMany(mappedBy = "order") // 연관관계 주인 설정
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate; // 여기서는 편의상 구분하지 않고 LocalDateTime으로 한다

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // enum 타입 클래스를 생성할 것

    // 양방향 연관관계이기 때문에 편의 메서드 생성한다
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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
