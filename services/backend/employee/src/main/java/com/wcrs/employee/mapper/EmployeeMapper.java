package com.wcrs.employee.mapper;

import com.wcrs.employee.dto.EmployeeRequestDTO;
import com.wcrs.employee.dto.EmployeeResponseDTO;
import com.wcrs.employee.dto.PhoneDTO;
import com.wcrs.employee.enums.Gender;
import com.wcrs.employee.enums.PhoneCategory;
import com.wcrs.employee.model.Employee;
import com.wcrs.employee.model.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    @Mapping(target = "gender", source = "gender", qualifiedByName = "stringToGender")
    @Mapping(target = "phone", source = "phone", qualifiedByName = "wrapPhoneInList")
    Employee toEmployee(EmployeeRequestDTO employeeRequestDTO);

    //@Mapping(target = "fullName", expression = "java(employee.getFullName())")
    @Mapping(target = "age", expression = "java(employee.getAge())")
    @Mapping(target = "phone", source = "phone", qualifiedByName = "toPhoneDtoList")
    @Mapping(target = "gender", source = "gender", qualifiedByName = "genderToString")
    EmployeeResponseDTO toEmployeeResponseDTO(Employee employee);

    // EmployeeResponseDTO toEmployeeResponseDTO(Page employee);

    @Mapping(target = "phoneCategory", source = "phoneCategory", qualifiedByName = "stringToPhoneCategory")
    Phone toPhone(PhoneDTO dto);


    @Named("stringToGender")
    default Gender stringToGender(String gender) {
        if (gender == null || gender.isEmpty())
            return null;
        try {
            return Gender.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Gender is not valid");
        }
    }

    @Named("wrapPhoneInList")
    default List<Phone> wrapPhoneInList(PhoneDTO phoneDTO) {
        if (phoneDTO == null) return Collections.emptyList();
        return List.of(toPhone(phoneDTO));
    }

    @Named("toPhoneDto")
    default PhoneDTO toPhoneDto(Phone phone) {
        if (phone == null) return null;

        PhoneDTO dto = new PhoneDTO();
        dto.setNumber(phone.getNumber());
        dto.setPhoneCategory(phone.getPhoneCategory() != null ? phoneCategoryToString(phone.getPhoneCategory()):null );
        dto.setCountryCode(phone.getCountryCode() !=null? phone.getCountryCode().toString(): null);
        return dto;
    }


    @Named("stringToPhoneCategory")
    default PhoneCategory stringToPhoneCategory(String category) {
        if (category == null) return null;
        return PhoneCategory.valueOf(category.toUpperCase());
    }

    @Named("phoneCategoryToString")
    default String phoneCategoryToString(PhoneCategory category) {
       return category !=null? category.toString():null;
    }
    @Named("genderToString")
    default String genderToString(Gender gender) {
        return gender != null? gender.toString():null;
    }

    @Named("toPhoneDtoList")
    default List<PhoneDTO> toPhoneDtoList(List<Phone> phones) {
        if (phones == null) return Collections.emptyList();
        return phones.stream().map(this::toPhoneDto).toList();
    }

}
