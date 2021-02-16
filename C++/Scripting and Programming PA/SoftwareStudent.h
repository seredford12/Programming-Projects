#pragma once
#include <string>
#include "Student.h"

class SoftwareStudent : public Student
{

private:

	Degree Program;
	
public:

	SoftwareStudent();
	SoftwareStudent(
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

	~SoftwareStudent();
};