package com.slowcloud.streak.member.model.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.slowcloud.streak.config.auth.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Member implements UserDetails {

	private static final long serialVersionUID = -8873071054876220259L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;

//	@NotBlank(message = "아이디는 반드시 입력해야 합니다.")
	@Column(unique = true)
	private String id;

//	@NotBlank(message = "비밀번호는 반드시 입력해야 합니다.")
//	@Min(value = 6, message = "비밀번호는 8자 이상으로 입력해 주세요.")
	private String password;

//	@NotBlank(message = "이름은 반드시 입력해야 합니다.")
	@Column(unique = true)
	private String name;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.toString()));
	}

	@Override
	public String getUsername() {
		return id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
