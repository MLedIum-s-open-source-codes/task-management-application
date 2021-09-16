package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.dto.DeskDto;
import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.exception.CustomException;
import com.example.taskmanagementapplication.repository.DeskRepository;
import com.example.taskmanagementapplication.service.DeskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Log4j2
@Service
@RequiredArgsConstructor
public class DeskServiceImpl implements DeskService {

  private final DeskRepository deskRepository;

  @Override
  public Desk create(DeskDto dto) {
    dto.setId(null);
    return update(dto.toDomain());
  }

  @Override
  public Desk get(Long id) {
    Optional<Desk> optional = deskRepository.findById(id);
    if (optional.isEmpty())
        throw new CustomException(ErrorTypeEnum.NOT_FOUND, format("Desk with id '%s' was not found", id));

    return optional.get();
  }

  @Override
  public Desk edit(DeskDto dto) {
    Desk desk = get(dto.getId());

    if (dto.getName() != null) {
      desk.setName(dto.getName());
    }

    return update(desk);
  }

  @Override
  public void checkExistsDeskWithId(Long id) {
    if (!existsDeskWithId(id))
        throw new CustomException(ErrorTypeEnum.NOT_FOUND, format("Desk with id '%s' was not found", id));
  }

  private boolean existsDeskWithId(Long id) {

    return deskRepository.existsById(id);
  }

  @Override
  public Desk update(Desk desk) {

    return deskRepository.save(desk);
  }

  @Override
  public void delete(Long id) {

    deskRepository.deleteById(id);
  }

}
