#pragma once
#include <string>
#include "Student.h"

class NetworkStudent : public Student
{
private:
	
	Degree Program;

public:

	NetworkStudent();
	NetworkStudent(
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

	~NetworkStudent();
};
