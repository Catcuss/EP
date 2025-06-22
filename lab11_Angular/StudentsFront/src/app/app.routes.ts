import { Routes } from '@angular/router';
import { StudentsComponent } from './students/students.component';
import { StudentDetailComponent } from './student-detail/student-detail.component';
import { AboutComponent } from './about/about.component';

export const routes: Routes = [
    { path: 'students', component: StudentsComponent },
    { path: '', redirectTo: '/students', pathMatch: 'full' },
    { path: 'student-detail/:id', component: StudentDetailComponent },
    { path: 'about', component: AboutComponent },

];