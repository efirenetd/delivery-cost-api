package org.efire.net.service;

import org.efire.net.dto.ItemDto;
import org.efire.net.model.Item;
import org.efire.net.model.Voucher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DeliveryService {

    private DeliveryCostCalculator deliveryCostCalculator;
    private RestTemplate restTemplate;

    public static final String VOUCHER_API_URL = "http://localhost:8080/api/v1/vouchers";

    public DeliveryService(DeliveryCostCalculator deliveryCostCalculator, RestTemplate restTemplate) {
        this.deliveryCostCalculator = deliveryCostCalculator;
        this.restTemplate = restTemplate;
    }

    public Item getCost(ItemDto dto) {
        Item item = Item.builder()
                .weight(dto.getWeight().floatValue())
                .height(dto.getHeight().floatValue())
                .width(dto.getWidth().floatValue())
                .length(dto.getLength().floatValue())
                .build();
        var calculatedItem = deliveryCostCalculator.calculateCost(item);
        if (StringUtils.hasText(dto.getVoucher())) {
            var voucherURI = UriComponentsBuilder.newInstance()
                    .fromHttpUrl(VOUCHER_API_URL)
                    .path("/{code}")
                    .buildAndExpand(dto.getVoucher()).toUri();

            var httpHeaders = new HttpHeaders();
            var requestEntity = new HttpEntity<String>(httpHeaders);

            var responseEntity = restTemplate.exchange(voucherURI, HttpMethod.GET, requestEntity, Voucher.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                var voucher = responseEntity.getBody();
                calculatedItem.setVoucher(voucher);
            }
        }
        return calculatedItem;
    }
}
