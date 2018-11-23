//defines wich users have acces to button	

	function findAction(action, button) {
		var neededRole = [];
		$.getJSON('/data/rolesinfo', function(data) {
			$.each(data, function(index, item) {
				if (item.actions.includes(action)) {
					neededRole.push(item.role.role);
					showButton(neededRole, button);
				}

			});

		});

	}

	//shows defined button

	function showButton(roles, button) {
		$
				.getJSON(
						'/data/userroles',
						function(data) {
							if (data != null) {
								$.each(
										data,
												function(index, item) {
													for (let i = 0; i < roles.length; i++) {
														if (item.role == roles[i]) {
															if (document
																	.getElementById(button) != null) {
																document
																		.getElementById(button).style.visibility = 'visible';
																console
																		.log("show Button " + button);
																return true;
															}
														}
													}

												});
							}

							else 
								//document.getElementById(button).style.visibility = 'hidden';
							return false;
							
							

						});
	}

	findAction("change", 'showAddFestBlock');
	findAction("signup", 'signup');
	findAction("addperf", "showPerfBlock");