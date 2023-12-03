export type InputProps = {
  id: string;
  type: string;
  label: string;
  disabled: boolean;
  value: string;
  onChange: (_: React.ChangeEvent<HTMLInputElement>) => void;
};

export const Input = ({ id, type, label, disabled, value, onChange }: InputProps) => (
  <input
    className="form-group__input"
    type={type}
    id={id}
    placeholder={label}
    value={value}
    disabled={disabled}
    onChange={onChange}
  />
);
