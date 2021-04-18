export class SpringResponse<T> {
  status: string;
  error: number;
  message: string;
  result: T;

  constructor(
      status: string,
      error: number,
      message: string,
      result: T
    ) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.result = result;
    }
}