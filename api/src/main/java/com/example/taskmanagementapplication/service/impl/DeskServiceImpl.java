package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.dto.DeskDto;
import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.entity.User;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.exception.CustomException;
import com.example.taskmanagementapplication.repository.DeskRepository;
import com.example.taskmanagementapplication.service.DeskService;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Log4j2
@Service
@RequiredArgsConstructor
public class DeskServiceImpl implements DeskService {

  private final DeskRepository deskRepository;

  @Override
  public Desk create(DeskDto deskDto) {
    deskDto.setId(null);
    return update(deskDto.toDomain());
  }

  @Override
  public Desk get(Long id) {
    Optional<Desk> desk = deskRepository.findById(id);
    if (desk.isEmpty()) {
      throw new CustomException(ErrorTypeEnum.NOT_FOUND, format("Desk with id '%s' was not found", id));
    }
    return desk.get();
  }

  @Override
  public Desk edit(DeskDto deskDto) {
    Desk desk = get(deskDto.getId());

    if (deskDto.getName() != null) {
      desk.setName(deskDto.getName());
    }

    return update(desk);
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
