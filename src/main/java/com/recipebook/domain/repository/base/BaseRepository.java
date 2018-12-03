package com.recipebook.domain.repository.base;

import com.recipebook.domain.model.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseRepository <T extends BaseEntity> extends CrudRepository<T, Long>, // crud actions
                                                               PagingAndSortingRepository<T, Long>, // paging
                                                               JpaSpecificationExecutor<T> //for filtering
{
}
