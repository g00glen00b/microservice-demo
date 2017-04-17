export const ALERT_ERROR_LEVEL: string = 'error';
export const ALERT_WARN_LEVEL: string = 'warn';
export const ALERT_INFO_LEVEL: string = 'info';
export const ALERT_SUCCESS_LEVEL: string = 'success';

export class Alert {
  level: string;
  message: string;


  constructor(level: string, message: string) {
    this.level = level;
    this.message = message;
  }
}
