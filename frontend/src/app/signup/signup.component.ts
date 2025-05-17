import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule],  // <-- Include here
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  name = '';
  mobile = '';
  email = '';
  password = '';
  vehicleId = '';

  register() {
    // TODO: Implement registration logic
    const user = {
      name: this.name,
      mobile: this.mobile,
      email: this.email,
      password: this.password,
      vehicleId: this.vehicleId,
      role: 'employee'
    };
    console.log(user);
  }
}
