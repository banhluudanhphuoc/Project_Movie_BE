package edu.kits.movie.Common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse <T> implements Serializable {
    private int currentPage;
    private int pageSize;
    private int totalPage;
    private List<T> data;
}
