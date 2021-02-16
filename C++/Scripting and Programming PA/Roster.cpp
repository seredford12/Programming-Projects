#include <iostream>
#include "Roster.h"
using std::cout;
using namespace std;

Roster::Roster()
{
	this->LastIndex = -1;
	this->Capacity = 0;
	this->ClassRosterArray = nullptr;
}

Roster::Roster(int Capacity)
{
	this->LastIndex = -1;
	this->Capacity = Capacity;
	this->ClassRosterArray = new Student * [Capacity];
}

Student* Roster::getStudentAt(int Index)
{
	return ClassRosterArray[Index];
}

void Roster::parseThenAdd(string datarow)
{
	if (LastIndex < Capacity)
	{
		LastIndex++;
		Degree Program;

		//Get ID
		int RightHandSide = datarow.find(",");
		string StudentID = datarow.substr(0, RightHandSide);

		//Get Student First Name
		int LeftHandSide = RightHandSide + 1;
		RightHandSide = datarow.find(",", LeftHandSide);
		string FirstName = datarow.substr(LeftHandSide, RightHandSide - LeftHandSide);

		//Get Student Last Name
		LeftHandSide = RightHandSide + 1;
		RightHandSide = datarow.find(",", LeftHandSide);
		string LastName = datarow.substr(LeftHandSide, RightHandSide - LeftHandSide);

		//Get Student Email
		LeftHandSide = RightHandSide + 1;
		RightHandSide = datarow.find(",", LeftHandSide);
		string EmailAddress = datarow.substr(LeftHandSide, RightHandSide - LeftHandSide);

		//Get Student Age
		LeftHandSide = RightHandSide + 1;
		RightHandSide = datarow.find(",", LeftHandSide);
		int Age = stoi(datarow.substr(LeftHandSide, RightHandSide - LeftHandSide));

		//Get Days In Course 1
		LeftHandSide = RightHandSide + 1;
		RightHandSide = datarow.find(",", LeftHandSide);
		int DaysInCourse1 = stoi(datarow.substr(LeftHandSide, RightHandSide - LeftHandSide));

		//Get Days In Course 2
		LeftHandSide = RightHandSide + 1;
		RightHandSide = datarow.find(",", LeftHandSide);
		int DaysInCourse2 = stoi(datarow.substr(LeftHandSide, RightHandSide - LeftHandSide));

		//Get Days In Course 3
		LeftHandSide = RightHandSide + 1;
		RightHandSide = datarow.find(",", LeftHandSide);
		int DaysInCourse3 = stoi(datarow.substr(LeftHandSide, RightHandSide - LeftHandSide));


		//Degree Program
		LeftHandSide = RightHandSide + 1;
		string D = datarow.substr(LeftHandSide, datarow.length() - LeftHandSide);
		if (D == "SECURITY")
		{
			Program = SECURITY;
		}
		else if (D == "NETWORK")
		{
			Program = NETWORK;
		}
		else if (D == "SOFTWARE")
		{
			Program = SOFTWARE;
		}

		add(StudentID, FirstName, LastName, EmailAddress, Age, DaysInCourse1, DaysInCourse2, DaysInCourse3, Program);


	}
	else
	{
		cout << "Error\n";
	}
}


void Roster::add(string StudentID, string FirstName, string LastName, string EmailAddress, int Age, int DaysInCourse1, int DaysInCourse2, int DaysInCourse3, Degree Program)
{

	int CourseDays[Student::CourseArraySize];
	CourseDays[0] = DaysInCourse1;
	CourseDays[1] = DaysInCourse2;
	CourseDays[2] = DaysInCourse3;

	if (Program == SECURITY)
	{
		ClassRosterArray[LastIndex] = new SecurityStudent(StudentID, FirstName, LastName, EmailAddress, Age, CourseDays, Program);
	}
	else if (Program == SOFTWARE)
	{
		ClassRosterArray[LastIndex] = new SoftwareStudent(StudentID, FirstName, LastName, EmailAddress, Age, CourseDays, Program);
	}
	else if (Program == NETWORK)
	{
		ClassRosterArray[LastIndex] = new NetworkStudent(StudentID, FirstName, LastName, EmailAddress, Age, CourseDays, Program);
	}
}


void Roster::remove(string StudentID)
{
	bool found = false;
	for (int i = 0; i <= LastIndex; i++)
	{

		if (ClassRosterArray[i]->getID() == StudentID)
		{
			found = true;
			delete this->ClassRosterArray[i];
			this->ClassRosterArray[i] = this->ClassRosterArray[LastIndex];
			LastIndex--;
			break;
		}
	}
	if (!found)
	{
		cout << "Student " << StudentID << " Not Found.\n";
	}
}

void Roster::printAll()
{
	cout << "\nDisplaying All Students:\n";
	for (int i = 0; i <= this->LastIndex; i++) (this->ClassRosterArray)[i]->print();
}

void Roster::printAverageDaysInCourse(string StudentID)
{
	bool found = false;
	for (int i = 0; i <= LastIndex; i++)
	{
		if (this->ClassRosterArray[i]->getID() == StudentID)
		{
			found = true;
			int* d = ClassRosterArray[i]->getDaysInCourse();
			cout << "Average Days In Course for student " << StudentID << " is " << (d[0] + d[1] + d[3]) / 3 << "\n";
		}
	}

}

void Roster::printInvalidEmails()
{
	cout << "\nDisplaying invalid email entries:\n";
	for (int i = 0; i <= LastIndex; i++)
	{
		string E = ClassRosterArray[i]->getEmail();
		bool isperiod = false;
		bool isspace = false;
		bool isat = false;
		if (E.find('.') != string::npos)
		{
			isperiod = true;
		}
		if (E.find(' ') != string::npos)
		{
			isspace = true;
		}
		if (E.find('@') != string::npos)
		{
			isat = true;
		}
		if (!(isperiod && isat && !isspace))
		{
			cout << E << " is invalid.\n\n";
		}
	}
}

void Roster::printByDegreeProgram(Degree DegreeProgram)
{
	cout << "\nPrinting Degree Program " << degreetypestrings[DegreeProgram] << "\n";
	for (int i = 0; i <= LastIndex; i++)
	{
		if (this->ClassRosterArray[i]->getDegreeProgram() == DegreeProgram) this->ClassRosterArray[i]->print();
	}

}

Roster::~Roster()
{
	for (int i = 0; i <= LastIndex; i++)
	{
		delete this->ClassRosterArray[i];
	}
	delete ClassRosterArray;
}




int main()
{

	int numStudents = 5;

	Roster* ClassRoster = new Roster(numStudents);

	const string StudentData[5] =

	{ "A1,John,Smith,John1989@gm ail.com,20,30,35,40,SECURITY",
	 "A2,Suzan,Erickson,Erickson_1990@gmailcom,19,50,30,40,NETWORK",
	 "A3,Jack,Napoli,The_lawyer99yahoo.com,19,20,40,33,SOFTWARE",
	 "A4,Erin,Black,Erin.black@comcast.net,22,50,58,40,SECURITY",
	 "A5,Sarah,Redford,seredford12@gmail.com,24,27,35,37,SOFTWARE"
	};


	for (int i = 0; i < numStudents; i++)
	{
		ClassRoster->parseThenAdd(StudentData[i]);
	}

	cout << "Scripting and Programming Applications, C++, StudentID: #001003030, Sarah Redford\n ";

	ClassRoster->printAll();

	ClassRoster->printInvalidEmails();

	for (int i = 0; i < numStudents; i++)
	{
		ClassRoster->printAverageDaysInCourse(ClassRoster->getStudentAt(i)->getID());
	}

	ClassRoster->printByDegreeProgram(SOFTWARE);

	cout << "\nRemoving Student\n";
	ClassRoster->remove("A3");

	cout << "\nRemoving Student\n";
	ClassRoster->remove("A3");


	ClassRoster->~Roster();


}