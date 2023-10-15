package com.ispan.eeit69.DTO;

public class OrderDto {
    private Integer orderNo;

    public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public OrderDto(Integer orderNo) {
        this.orderNo = orderNo;
    }
	@Override
    public String toString() {
        return "OrderDto{" +
                 + orderNo +
                '}';
    }

}
