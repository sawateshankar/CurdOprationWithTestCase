import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { error } from 'console';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrl: './update-user.component.css'
})
export class UpdateUserComponent implements OnInit {

  id:number;
  user:User = new User();
constructor(private userService:UserService,private route: ActivatedRoute,
  private router:Router){
this.id=0;
}
ngOnInit(): void {
  this.id = +this.route.snapshot.params['id'];
  this.userService.getUserById(this.id).subscribe(
    (data: User) => {
      this.user = data;
    },
    (error) => {
      console.error('Error fetching user data:', error);
      // Handle error, e.g., redirect to an error page or show an error message
    }
  );
}
 onSubmit() {

  this.userService.updateUser(this.id,this.user).subscribe(date=>{
    this.goToUserList();
  },
  error=>console.log(error)
  )
    console.log(); 
  }

  goToUserList(){
    this.router.navigate(['/users']);
    }
}
