import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login';
import { MainLayout } from './components/main-layout/main-layout';
import { DashboardComponent } from './components/dashboard/dashboard';
import { TicketListComponent } from './components/ticket-list/ticket-list';
import { TicketFormComponent } from './components/ticket-form/ticket-form';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    {
        path: 'main',
        component: MainLayout,
        children: [
            { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
            { path: 'dashboard', component: DashboardComponent },
            { path: 'tickets', component: TicketListComponent },
            { path: 'tickets/new', component: TicketFormComponent },
            { path: 'tickets/edit/:id', component: TicketFormComponent }
        ]
    }
];
