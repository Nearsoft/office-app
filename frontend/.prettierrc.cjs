module.exports = {
  plugins: ['@trivago/prettier-plugin-sort-imports'],
  tabWidth: 2,
  useTabs: false,
  semi: true,
  singleQuote: true,
  printWidth: 100,
  trailingComma: 'all',
  importOrder: ['^(^react$|@react|react|^recoil$)', '<THIRD_PARTY_MODULES>', '^@/(.*)$', '^[./]'],
  importOrderGroupNamespaceSpecifiers: true,
  importOrderSeparation: true,
  importOrderSortSpecifiers: true,
};
