package com.iiproject.donationpj.helper;

import com.iiproject.donationpj.entity.Donation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntityHelper {
    Page<Donation> entityPage;
    String errorMessage;
    List<Donation> listSearched;
}
