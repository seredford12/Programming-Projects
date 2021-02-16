#pragma once
#include <string>
#include "Student.h"

class SecurityStudent : public Student
{
private:

	Degree Program;

public:

	SecurityStudent();
	SecurityStudent(
		string StudentID,
		string FirstName,
		string LastName,
		string EmailAddress,
		int Age,
		int DaysInCourse[],
		Degree pro
	);

		Degree getDegreeProgram();

		void print();

		~SecurityStudent();

};