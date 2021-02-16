#include <iostream>
#include "SoftwareStudent.h"
using std::cout;

SoftwareStudent::SoftwareStudent() :Student()
{
	Program = SOFTWARE;
}

SoftwareStudent::SoftwareStudent(string StudentID, string FirstName, string LastName, string EmailAddress, int Age, int DaysInCourse[], Degree pro)
	: Student(StudentID, FirstName, LastName, EmailAddress, Age, DaysInCourse)
{
	Program = pro;
}

Degree SoftwareStudent::getDegreeProgram()
{
	return Program;
}

void SoftwareStudent::print()
{
	cout << getID() << "\t First Name: " << getFirst() << "\t Last Name: " << getLast() << "\t Email Address: " << getEmail() << "\t Age: " << getA() << "\t Days In Course: {" << getDaysInCourse()[0] << ", " << getDaysInCourse()[1] << ", " << getDaysInCourse()[2] << "}" << "\t Degree: SOFTWARE" << "\n";
}

SoftwareStudent::~SoftwareStudent()
{
	
}