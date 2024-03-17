import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { UserDetailsComponent } from './user-details/user-details.component';

const routes: Routes = [
  {path:'users',component:UserListComponent},
  {path:'create-user', component:CreateUserComponent},
  { path: 'update-user/:id', component: UpdateUserComponent},
  {path:'delete-user/:id',component:UserListComponent},
  {path:'user-details/:id', component:UserDetailsComponent},
  {path:'',redirectTo:'users',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
