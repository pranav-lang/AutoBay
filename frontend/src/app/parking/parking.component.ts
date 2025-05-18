import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';  // ✅ Add this line

@Component({
  selector: 'app-parking',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule // ✅ Add FormsModule to imports array
  ],
  templateUrl: './parking.component.html',
  styleUrls: ['./parking.component.scss']
})
export class ParkingComponent implements OnInit {
  zones: string[] = ['Zone A', 'Zone B', 'Zone C'];
  selectedZone: string | null = null;
  parkingSlots: any[] = [];
  showModal = false;
  selectedSlot: any = null;
  bookingData = {
    vehicleId: '',
    vehicleType: ''
  };
  showTimer = false;
  remainingTime = '05:00';
  private timerInterval: any;

  ngOnInit() {
    // Initialize default zone
    this.selectZone('Zone A');
  }

  selectZone(zone: string) {
    this.selectedZone = zone;
    this.generateSlots();
  }

  generateSlots() {
    this.parkingSlots = Array.from({ length: 30 }, (_, i) => {
      const status = ['available', 'occupied', 'reserved'][Math.floor(Math.random() * 3)];
      return { label: `${this.selectedZone}-${i + 1}`, status };
    });
  }

  selectSlot(slot: any) {
    if (slot.status !== 'available') return;
    this.selectedSlot = slot;
    this.showModal = true;
  }

  bookSlot() {
    if (this.selectedSlot) {
      this.selectedSlot.status = 'reserved';
      this.showModal = false;
      this.startTimer();
    }
  }

  closeModal() {
    this.showModal = false;
  }

  startTimer() {
    let seconds = 300;
    this.showTimer = true;

    this.timerInterval = setInterval(() => {
      seconds--;
      const mins = Math.floor(seconds / 60);
      const secs = seconds % 60;
      this.remainingTime = `${this.pad(mins)}:${this.pad(secs)}`;
      if (seconds <= 0) {
        clearInterval(this.timerInterval);
        this.showTimer = false;
      }
    }, 1000);
  }

  pad(value: number) {
    return value < 10 ? `0${value}` : value.toString();
  }
}
