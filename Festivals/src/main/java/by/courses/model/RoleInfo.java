package by.courses.model;

import java.util.HashSet;
import java.util.Set;

public class RoleInfo {

	private Role role;
	private Set<String> actions = new HashSet<>();

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<String> getActions() {
		return actions;
	}

	public void setActions(Set<String> actions) {
		this.actions = actions;
	}

}
