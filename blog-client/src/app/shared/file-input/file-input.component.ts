import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-file-input',
  templateUrl: './file-input.component.html'
})
export class FileInputComponent implements OnInit {
  @Output() fileChange: EventEmitter<FileList> = new EventEmitter<FileList>();

  constructor() { }

  ngOnInit() {
  }

  onChange(event: EventTarget) {
    const eventObj: MSInputMethodContext = <MSInputMethodContext> event;
    const target: HTMLInputElement = <HTMLInputElement> eventObj.target;
    this.fileChange.emit(target.files);
  }
}
